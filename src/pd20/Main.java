package pd20;

import pd20.gateway.ApiGateway;
import pd20.client.*;
import pd20.writer.ReportWriter;

public class Main {
    public static void main(String[] args) {
        ApiGateway apiGateway = new ApiGateway(new UserClient(new TodosClient(), new PostsClient()), new WeatherClient(), new CountryClient());
        ReportWriter reportWriter = new ReportWriter();
        System.out.println(apiGateway.getCountryInfo("poland"));
        reportWriter.write(apiGateway.getUserRaport(10L));
    }
}
