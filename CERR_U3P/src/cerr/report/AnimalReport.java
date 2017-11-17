package cerr.report;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cerr.dao.AnimalDAOImpl;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * Servlet implementation class AnimalReport
 */
@WebServlet("/AnimalReport")
public class AnimalReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AnimalDAOImpl ani;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalReport() {
        super();
        ani = new AnimalDAOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reportPath = "C:\\Users\\Eduardo\\eclipse-workspace\\CERR_U3P\\src\\cerr\\report\\animal_report.jrxml";
		try {
			JasperReport report = JasperCompileManager.compileReport(reportPath);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("imagen_mia", this.getServletContext().getRealPath("/")+"images/imagen_mia.jpg");
			JasperPrint print = JasperFillManager.fillReport(report, data, ani.getConnection());
			JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}catch(JRException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
