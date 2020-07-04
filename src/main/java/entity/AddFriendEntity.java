package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class AddFriendEntity extends AbstractEntity {
    private Integer id;
    private String nickName1;
    private String nickName2;
}
