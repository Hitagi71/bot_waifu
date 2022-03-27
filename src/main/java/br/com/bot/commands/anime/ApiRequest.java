package br.com.bot.commands.anime;

public class ApiRequest {
    private String query;
    private Variables variables;

    public ApiRequest(String query, Variables variables) {
        this.query = query;
        this.variables = variables;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Variables getVariables() {
        return variables;
    }

    public void setVariables(Variables variables) {
        this.variables = variables;
    }
}
