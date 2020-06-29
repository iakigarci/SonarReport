package view;

import java.util.ArrayList;

import configuration.ReportConfiguration;
import configuration.SonarRequestList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SonarRequestList sonarRList = SonarRequestList.getSonarRequestList();
		ReportConfiguration conf = new ReportConfiguration("93a572d27e28bc010ef64c0af5ea395953cd66ad", "darkchess", "master", "iaki", "1.0.3-SNAPSHOT");
		ArrayList<ReportConfiguration> l = new ArrayList<ReportConfiguration>();
		l.add(conf);
		sonarRList.execute(l);
	}

}
