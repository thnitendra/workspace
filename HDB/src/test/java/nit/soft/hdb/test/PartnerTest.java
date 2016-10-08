package nit.soft.hdb.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionManagerImple;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionSynchronizationRegistryImple;
import com.arjuna.ats.jta.common.JTAEnvironmentBean;

import nit.soft.partner.model.Address;
import nit.soft.partner.model.BaseAddress;
import nit.soft.partner.service.PartnerService;

public class PartnerTest {
	
	private TestUtil util;		
	private PartnerService service;		
	
	public static void main(String[] args) {
		System.out.println(JTAEnvironmentBean.class.getSimpleName() + "." + "transactionManagerClassName="+ TransactionManagerImple.class.getName());  
		System.out.println(JTAEnvironmentBean.class.getSimpleName() + "." + "transactionSynchronizationRegistryClassName=" + TransactionSynchronizationRegistryImple.class.getName());  
	}
	
	public PartnerTest() {		
		System.setProperty("java.util.logging.config.file", "src/main/resources/logging.properties");		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:test-context.xml");
		util = ctx.getBean("testUtil", TestUtil.class);
		service = ctx.getBean("partnerService", PartnerService.class);
	}
			
	public void addressTest() throws IOException {
		BaseAddress[] badd = util.getBaseAddress();
		Address[] addrs = util.getAddresses();
		addrs[1].setBaseAddress(badd[0]);
		addrs[1].setStreet("street");
		addrs[2].setBaseAddress(badd[0]);
		addrs[2].setStreet("street");
		service.save(Arrays.asList(new Address[] {addrs[1], addrs[2]} ));
 	}
	
	@Test
	public void versionTest() throws IOException {
		BaseAddress[] badd = util.getBaseAddress();
		List<BaseAddress> baddlst = service.find(badd[0], BaseAddress.class);
		System.out.println(baddlst.get(0).getId() + " and now udating...");
		service.update(baddlst.get(0));
		//service.findAndUpdate(badd[0]);
	}
	
}
