package files.POJOClasses.Request;

import com.google.gson.annotations.SerializedName;

//import io.restassured.mapper.ObjectMapper;


public class GitApiPojo {

    private String name, description, homepage;
    @SerializedName("private")
    private boolean isPrivate;
    private boolean has_issues, has_projects, has_wiki;

    public String convertJsonIntoString()
    {
        return name + "," + description + "," + homepage + "," + isPrivate + "," + has_issues + "," + has_projects + "," + has_wiki;
    }

    public GitApiPojo(String name, String description, String homepage, boolean isPrivate, boolean has_issues, boolean has_projects, boolean has_wiki)
    {
        this.name = name;
        this.description = description;
        this.homepage = homepage;
        this.isPrivate = isPrivate;
        this.has_issues = has_issues;
        this.has_projects = has_projects;
        this.has_wiki = has_wiki;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHomepage() {
        return homepage;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public boolean isHas_issues() {
        return has_issues;
    }

    public boolean isHas_projects() {
        return has_projects;
    }

    public boolean isHas_wiki() {
        return has_wiki;
    }
}
