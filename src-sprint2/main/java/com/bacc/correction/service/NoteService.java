package com.bacc.correction.service;

import com.bacc.correction.model.*;
import com.bacc.correction.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ParametreRepository parametreRepository;

    @Autowired
    private NoteFinalRepository noteFinalRepository;

    public Double calculateFinalNote(Long candidatId, Long matiereId) {
        List<Note> notes = noteRepository.findByCandidatIdAndMatiereId(candidatId, matiereId);
        if (notes.isEmpty()) return null;
        if (notes.size() == 1) return notes.get(0).getNote();

        double sumDiff = 0;
        List<Double> noteValues = notes.stream().map(Note::getNote).collect(Collectors.toList());
        for (int i = 0; i < noteValues.size(); i++) {
            for (int j = i + 1; j < noteValues.size(); j++) {
                sumDiff += Math.abs(noteValues.get(i) - noteValues.get(j));
            }
        }

        List<Parametre> parametres = parametreRepository.findByMatiereId(matiereId);

        double finalNote = noteValues.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        if (!parametres.isEmpty()) {
            Double closestDiff = null;
            double minDistance = Double.MAX_VALUE;

            for (Parametre p : parametres) {
                double distance = Math.abs(sumDiff - p.getDiff());
                if (distance < minDistance - 1e-9) { 
                    minDistance = distance;
                    closestDiff = p.getDiff();
                } else if (Math.abs(distance - minDistance) <= 1e-9) {
                    if (closestDiff != null && p.getDiff() < closestDiff) {
                        closestDiff = p.getDiff();
                    }
                }
            }

            if (closestDiff != null) {
                final Double selectedDiff = closestDiff;
                List<Parametre> chosenParametres = parametres.stream()
                        .filter(p -> Math.abs(p.getDiff() - selectedDiff) <= 1e-9)
                        .collect(Collectors.toList());

                for (Parametre p : chosenParametres) {
                    boolean matched = checkCondition(sumDiff, p.getOperateur().getSymbole(), p.getDiff());
                    if (matched) {
                        finalNote = applyResolution(noteValues, p.getResolution().getNom());
                        break;
                    }
                }
            }
        }
        
        return finalNote;
    }

    private boolean checkCondition(double val, String operator, double threshold) {
        switch (operator) {
            case "<": return val < threshold;
            case ">": return val > threshold;
            case "<=": return val <= threshold;
            case ">=": return val >= threshold;
            case "=": return val == threshold;
            default: return false;
        }
    }

    private double applyResolution(List<Double> notes, String resolution) {
        if (notes.isEmpty()) return 0.0;
        String res = resolution.toLowerCase();
        if (res.contains("petit")) {
            return notes.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
        } else if (res.contains("grand")) {
            return notes.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
        } else {
            return notes.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        }
    }


    public void saveFinalNotesForAll() {
        noteRepository.findAll().stream()
            .map(n -> new Pair(n.getCandidat().getId(), n.getMatiere().getId()))
            .distinct()
            .forEach(pair -> {
                Double finalNoteVal = calculateFinalNote(pair.candidatId, pair.matiereId);
                if (finalNoteVal != null) {
                    NoteFinal nf = noteFinalRepository.findByCandidatIdAndMatiereId(pair.candidatId, pair.matiereId)
                            .orElse(new NoteFinal());

                    Note firstNote = noteRepository.findByCandidatIdAndMatiereId(pair.candidatId, pair.matiereId).get(0);
                    nf.setCandidat(firstNote.getCandidat());
                    nf.setMatiere(firstNote.getMatiere());
                    nf.setNoteFinal(finalNoteVal);
                    noteFinalRepository.save(nf);
                }
            });
    }

    private static class Pair {
        Long candidatId;
        Long matiereId;
        Pair(Long c, Long m) { this.candidatId = c; this.matiereId = m; }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return java.util.Objects.equals(candidatId, pair.candidatId) && java.util.Objects.equals(matiereId, pair.matiereId);
        }
        @Override
        public int hashCode() { return java.util.Objects.hash(candidatId, matiereId); }
    }

    public List<NoteFinal> getAllFinalNotes() {
        saveFinalNotesForAll();
        return noteFinalRepository.findAll();
    }
}

