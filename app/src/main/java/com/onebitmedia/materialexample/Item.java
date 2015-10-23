package com.onebitmedia.materialexample;

/**
 * @author rakawm
 */
public class Item {
    public static final String TYPE_SAMPLE = "sample";
    public static final String TYPE_OPTION = "option    ";

    private String title;
    private String description;
    private String type;

    public Item(String title, String description, String type) {
        setTitle(title);
        setDescription(description);
        setType(type);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
