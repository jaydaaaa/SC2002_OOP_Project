package boundary;
import entity.CentralManager;
import entity.Suggestion;

import java.util.ArrayList;
import java.util.Objects;

public class SuggestionBoundary extends BaseBoundary {
    public SuggestionBoundary(CentralManager centralManager) {
        super(centralManager);
    }

	public void printSuggestionFormat() {
        System.out.println("Camp | Suggestor | Status | Suggestion");
    }
}