import java.util.List;

import com.newchieve.Application;
import com.newchieve.crm.entity.Country;
import com.newchieve.crm.service.CountryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class})
public class CountryServiceTest {

	@Autowired
	private CountryService countryService;

	@Test
	void test() {
		List<Country> list = countryService.list();
		System.out.println(list);
	}

}