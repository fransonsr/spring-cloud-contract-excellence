import org.springframework.cloud.contract.spec.Contract;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


Contract.make {
	description "list videos"
	request {
		url "/videos"
		method GET()
	}
	response {
		status 200
		headers {
			header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
		}
		body([[id: 1L, title: "Dumbo", rating: "G"], [id: 2, title: "Star Wars", rating: "PG"], [id: 3, title: "Dune", rating: "PG-13"]])
	}
}