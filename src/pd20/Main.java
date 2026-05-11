package pd20;

import pd20.gateway.ApiGateway;
import pd20.service.*;
import pd20.writer.ReportWriter;

public class Main {
    public static void main(String[] args) {
        ApiGateway apiGateway = new ApiGateway(new UserService(), new WeatherService(), new TodosService(),
                new PostsService(), new CountryService());
        ReportWriter reportWriter = new ReportWriter();
        System.out.println(apiGateway.getCountryInfo("poland"));
        reportWriter.write(apiGateway.getUserRaport(10L));
    }
}
