package use_case.search;

public interface SearchOutputBoundary {

    void prepareSuccessView(SearchOutputData product);

    void prepareFailView(String error);
}
