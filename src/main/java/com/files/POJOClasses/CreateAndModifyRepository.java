package com.files.POJOClasses;

public class CreateAndModifyRepository {
        private String has_issues;

        private String has_projects;

        private String description;

        private String name;

        private String has_wiki;

        private String homepage;

        private String privateString;

        public String getHas_issues ()
        {
            return has_issues;
        }

        public void setHas_issues (String has_issues)
        {
            this.has_issues = has_issues;
        }

        public String getHas_projects ()
        {
            return has_projects;
        }

        public void setHas_projects (String has_projects)
        {
            this.has_projects = has_projects;
        }

        public String getDescription ()
        {
            return description;
        }

        public void setDescription (String description)
        {
            this.description = description;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String value)
        {
            name = "Repo_created"+value;
            this.name = name;
        }

        public String getHas_wiki ()
        {
            return has_wiki;
        }

        public void setHas_wiki (String has_wiki)
        {
            this.has_wiki = has_wiki;
        }

        public String getHomepage ()
        {
            return homepage;
        }

        public void setHomepage (String homepage)
        {
            this.homepage = homepage;
        }

        public String getPrivate ()
        {
            return privateString;
        }

        public void setPrivate (String privateString)
        {
            this.privateString = privateString;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [has_issues = "+has_issues+", has_projects = "+has_projects+", description = "+description+", name = "+name+", has_wiki = "+has_wiki+", homepage = "+homepage+", private = "+privateString+"]";
        }
}

