import com.fasterxml.jackson.core.JsonProcessingException;
import com.newchieve.Application;
import com.newchieve.crm.service.TencentLeadsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class})
public class TencentLeadsServiceTest {

	@Autowired
	private TencentLeadsService tencentLeadsService;

	@Test
	void test(){
		try {
			tencentLeadsService.syncData();
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
