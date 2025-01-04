package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompletedTask {
    private String taskName;
    private String taskAssignedDate;
    private String taskCompletedDate;
}
