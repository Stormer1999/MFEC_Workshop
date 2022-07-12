package th.co.mfec.api.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class StatusResponse {

    private String code;

    private String desc;

    private Date time;
}
