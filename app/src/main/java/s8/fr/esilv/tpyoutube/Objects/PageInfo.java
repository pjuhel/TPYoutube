package s8.fr.esilv.tpyoutube.Objects;

/**
 * Created by juhel on 11/03/2016.
 */
public class PageInfo {
    private String totalResult;
    private String resultPerPage;

    public String getResultPerPage() {
        return resultPerPage;
    }

    public void setResultPerPage(String resultPerPage) {
        this.resultPerPage = resultPerPage;
    }

    public String getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }
}
