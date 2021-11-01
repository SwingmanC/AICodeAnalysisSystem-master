package org.nju.demo.pojo;

import java.util.ArrayList;

public class BugInstance {
    private String type;
    private String category;
    private int priority;
    private String classname;
    private String sourcePath;
    private int start;
    private int end;
    private ArrayList<Method> methodList;

    public BugInstance() {
        start = -1;
        end = -1;
        methodList = new ArrayList<>();
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getClassname() {
        return classname;
    }
    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getSourcePath() {
        return sourcePath;
    }
    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public ArrayList<Method> getMethodList() {
        return methodList;
    }
    public void setMethodList(ArrayList<Method> methodList) {
        this.methodList = methodList;
    }
    public void add(Method method) {
        methodList.add(method);
    }
}
