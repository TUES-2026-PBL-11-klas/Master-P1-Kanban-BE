package Master.Kanban.model;

public record Task(
    int index,
    long UsrAuthT,
    boolean deleted,
    String title,
    String desc,
    int priority,
    int state,
    String tszImplement //not implemented
) {}
