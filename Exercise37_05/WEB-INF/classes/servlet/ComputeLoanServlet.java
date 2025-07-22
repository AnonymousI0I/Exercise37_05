package servlet;

import model.Loan;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/computeLoan")
public class ComputeLoanServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    double amount = parse(req.getParameter("amount"));
    double rate   = parse(req.getParameter("rate"));
    int years     = (int) parse(req.getParameter("years"));

    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();

    if (amount <= 0 || rate <= 0 || years <= 0) {
      out.println("<h3 style='color:red'>Invalid input.</h3>");
      return;
    }

    Loan loan = new Loan(rate, years, amount);

    out.printf("<p>Loan Amount: %.2f</p>%n", amount);
    out.printf("<p>Annual Interest Rate: %.2f</p>%n", rate);
    out.printf("<p>Number of Years: %d</p>%n", years);
    out.printf("<p><b>Monthly Payment: %.2f</b></p>%n", loan.getMonthlyPayment());
    out.printf("<p><b>Total Payment: %.2f</b></p>%n", loan.getTotalPayment());
  }

  private double parse(String s) {
    try { return Double.parseDouble(s); } catch (Exception e) { return -1; }
  }
}
