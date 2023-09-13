package account;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({
	"account.hexagonal.domain",
	"account.hexagonal.service",
	"account.adapter.controller"
})
public class FinalView {

}
