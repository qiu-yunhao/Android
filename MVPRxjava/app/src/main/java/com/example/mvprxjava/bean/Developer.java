package com.example.mvprxjava.bean;

import java.util.List;

public class Developer {
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
        private String user;
        private String user_link;
        private String full_name;
        private String developer_avatar;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getUser_link() {
            return user_link;
        }

        public void setUser_link(String user_link) {
            this.user_link = user_link;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public String getDeveloper_avatar() {
            return developer_avatar;
        }

        public void setDeveloper_avatar(String developer_avatar) {
            this.developer_avatar = developer_avatar;
        }
    }
}
