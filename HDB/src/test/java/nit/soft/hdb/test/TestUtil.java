package nit.soft.hdb.test;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.core.io.Resource;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import nit.soft.partner.model.Address;
import nit.soft.partner.model.BaseAddress;

public class TestUtil {
	private Resource jsonBaseAddress;
	private Resource jsonAddress;
	private Gson gson;
	
	public TestUtil() {
		gson = new Gson();
	}
	
	public void setJsonBaseAddress(Resource jsonBaseAddress) {
		this.jsonBaseAddress = jsonBaseAddress;
	}
	
	public void setJsonAddress(Resource jsonAddress) {
		this.jsonAddress = jsonAddress;
	}
	
	public BaseAddress[] getBaseAddress() throws IOException {
		return gson.fromJson(new JsonReader(new FileReader(jsonBaseAddress.getFile())), BaseAddress[].class);
	}	
	public Address[] getAddresses() throws IOException {
		return gson.fromJson(new JsonReader(new FileReader(jsonAddress.getFile())), Address[].class);
	}	
}
