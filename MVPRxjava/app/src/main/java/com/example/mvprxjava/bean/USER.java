package com.example.mvprxjava.bean;

import java.util.List;

public class USER {
    private int count;
    private String msg;
    private List<ItemsDTO> items;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ItemsDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemsDTO> items) {
        this.items = items;
    }

    public static class ItemsDTO {
        private String repo;
        private String repo_link;
        private String desc;
        private String lang;
        private String stars;
        private String forks;
        private String added_stars;
        private List<String> avatars;
        

        public String getRepo() {
            return repo;
        }

        public void setRepo(String repo) {
            this.repo = repo;
        }

        public String getRepo_link() {
            return repo_link;
        }

        public void setRepo_link(String repo_link) {
            this.repo_link = repo_link;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public String getForks() {
            return forks;
        }

        public void setForks(String forks) {
            this.forks = forks;
        }

        public String getAdded_stars() {
            return added_stars;
        }

        public void setAdded_stars(String added_stars) {
            this.added_stars = added_stars;
        }

        public List<String> getAvatars() {
            return avatars;
        }

        public void setAvatars(List<String> avatars) {
            this.avatars = avatars;
        }
    }
}
