<%@ page import="java.util.Calendar" language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>
<%@ page import="model.Pet" %>
<%@ page import="model.service.TaskManager" %>
<%@ page import="model.service.PetManager" %>
<%@ page import="model.dao.TaskDAO" %>
<%@ page import="model.dao.PetDAO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>calendar</title>
<link href="../css/calendar_style.css" rel="stylesheet">

<%!String today(int a) {
      String t = "";
      String d = "";
      if (a != 1) {
         t = "<th align = \"center\">";
         if (a == 2)
            d = "월";
         else if (a == 3)
            d = "화";
         else if (a == 4)
            d = "수";
         else if (a == 5)
            d = "목";
         else if (a == 6)
            d = "금";
         else
            d = "토";
      } else {
         t = "<th align = \"center\" class = \"Sunday\">";
         d = "일";
      }
      return t + d;
   }

   public String parseDay(int cnt) {
      String t = "";
      if (cnt % 7 == 1)
         t = "<td align = \"center\" class = \"Sunday\" style=\"width:100px;\">";
      else
         t = "<td align = \"center\" style=\"width:100px;\">";
      return t;
   }%>
</head>
<body>
<c:set var = "syear" value="${syear}" />
<c:set var = "smonth" value="${smonth}" />
<c:set var = "allTask" value="${allTask}" />
<c:set var = "pet" value="${pet}" />
   <%
      List<Task> allTask = (List<Task>)pageContext.getAttribute("allTask");
      List<Pet> pet = (List<Pet>)pageContext.getAttribute("pet");
      
      Calendar today = Calendar.getInstance();
      int date = today.get(Calendar.DATE);
      int day = today.get(Calendar.DAY_OF_WEEK);
      int start = today.getMinimum(Calendar.DATE);
      int end;
      String sYear = (String)pageContext.getAttribute("syear");
      String sMonth = (String)pageContext.getAttribute("smonth");
      int year, month;
      if (sYear == null)
         year = today.get(Calendar.YEAR);
      else
         year = Integer.parseInt(sYear);

      if (sMonth == null)
         month = today.get(Calendar.MONTH) + 1;
      else
         month = Integer.parseInt(sMonth);

      Calendar cal = Calendar.getInstance();
      cal.set(year, month - 1, 1);
      
      int cnt = 0;
      int i;
      
      out.print("<form method=post>");
      out.print("<div align = \"center\">");
      out.print("<table class = \"calendarTop\">");
      out.print("<tr style='font-size: 30px;'>");
      out.print("<td align = \"left\">");
      
      pageContext.setAttribute("year", year) ;
      pageContext.setAttribute("month", month);
      
      %>
      <a href = "<c:url value='/task/select'>
                  <c:param name='userId' value="${userId}"/>
                  <c:param name='password' value="${password}"/>
                  <c:param name='petId' value="${petId}"/>
                  <c:param name='date' value="${date}"/>
                  <c:param name='selectPet' value="${selectPet}"/>
                  <c:param name='task' value="${task}"/>
                  <c:param name='introduce' value="${introduce}"/>
                  
                  <c:param name='decreaseYear' value= "deY" />
                  <c:param name='year' value="${year}"/>
                  <c:param name='month' value="${month}"/>
               </c:url>">
         ◀
      </a>
      
      <%
      out.print("<b>" + year + "년");
      %>
      
      <a href = "<c:url value='/task/select'>
                  <c:param name='userId' value="${userId}"/>
                  <c:param name='password' value="${password}"/>
                  <c:param name='petId' value="${petId}"/>
                  <c:param name='date' value="${date}"/>
                  <c:param name='selectPet' value="${selectPet}"/>
                  <c:param name='task' value="${task}"/>
                  <c:param name='introduce' value="${introduce}"/>
                  
                  <c:param name='increaseYear' value= "inY" />
                  <c:param name='year' value="${year}"/>
                  <c:param name='month' value="${month}"/>
               </c:url>">
         ▶
      </a>
      
      <% 
      out.print("</td><td align = \"center\">");
      %>
      <a href = "<c:url value='/task/select'>
                  <c:param name='userId' value="${userId}"/>
                  <c:param name='password' value="${password}"/>
                  <c:param name='petId' value="${petId}"/>
                  <c:param name='date' value="${date}"/>
                  <c:param name='selectPet' value="${selectPet}"/>
                  <c:param name='task' value="${task}"/>
                  <c:param name='introduce' value="${introduce}"/>
                  
                  <c:param name='decreaseMonth' value= "deM" />
                  <c:param name='year' value="${year}"/>
                  <c:param name='month' value="${month}"/>
               </c:url>">
         ◀
      </a>
      
      
      <% 
      out.print("<b>" + month + "월");
      %>
   
      <a href = "<c:url value='/task/select'>
                  <c:param name='userId' value="${userId}"/>
                  <c:param name='password' value="${password}"/>
                  <c:param name='petId' value="${petId}"/>
                  <c:param name='date' value="${date}"/>
                  <c:param name='selectPet' value="${selectPet}"/>
                  <c:param name='task' value="${task}"/>
                  <c:param name='introduce' value="${introduce}"/>
                  
                  <c:param name='increaseMonth' value= "inM" />
                  <c:param name='year' value="${year}"/>
                  <c:param name='month' value="${month}"/>
               </c:url>">
         ▶
      </a>
<% 
      out.print("</td><td align = \"right\"> <b>" + today.get(Calendar.YEAR) + "-" + (today.get(Calendar.MONTH) + 1)
            + "-" + today.get(Calendar.DATE) + "<td>");
      out.print("</tr>");
      out.print("</table>");
      out.print("<br>");

      out.print("<table border=\"1\" class = \"calendarTable\">");
      
      day = cal.get(Calendar.DAY_OF_WEEK);
      out.print("<tr align = \"center\" style=\"height:50px;\" >");
      for (i = 0; i < 7; i++) {
         out.print(today(i + 1));
         out.print("</th>");
      }
      out.print("</tr>");
      
      
      out.print("<tr align = \"center\" style=\"height:120px;\">");
      
      cal.set(year, month, 1-1);
      end = cal.get(Calendar.DATE);
      pageContext.setAttribute("end", end);
      pageContext.setAttribute("cnt", cnt);
      pageContext.setAttribute("yearMonth",  Integer.toString(year) +  Integer.toString(month));

      for (date = 1; date <= end; date++) {
         cnt++;
         out.print(parseDay(cnt));
         out.print("<div style=\"text-align: left; height:20px;\">");
         out.print("<p>" + "&nbsp;" + date + "</p>");
         out.print("</div>");
         int sdate = 0;
         int edate = 0;
         int currentDay = 0;
         String m =  String.valueOf(month);
         
         if(month < 10)
            m = "0" + String.valueOf(month);
         if(date < 10)
            currentDay = Integer.parseInt(String.valueOf(year) + m + "0" + String.valueOf(date));
         else
            currentDay = Integer.parseInt(String.valueOf(year) + m + String.valueOf(date));
         System.out.println("currentDay : " + currentDay);
         
         ArrayList<String> list = new ArrayList<String>();
         
         out.print("<div style=\"height:100px;\">");
         for(Task t : allTask){
            sdate = Integer.parseInt(t.getStartdate());
            edate = Integer.parseInt(t.getEnddate());
            
            String name = null;
            if(currentDay >= sdate && currentDay <= edate){
               for(Pet p : pet){
                  if(p.getPetId() == t.getPetId()){
                     name = p.getName();
                  
                     if(!list.contains(name)){
                        list.add(name);
                        //out.print("<br>" + p.getName());
                        pageContext.setAttribute("cPet", p.getName()) ;
                        pageContext.setAttribute("selectPet", p.getPetId());
                        pageContext.setAttribute("currentDay", currentDay) ;
   %>
                     
                     <br>
                     <a href = "<c:url value='/task/select'>
                                 <c:param name='calendarPet' value="${cPet}"/>
                                 <c:param name='year' value="${year}"/>
                                 <c:param name='month' value="${month}"/>
                                 <c:param name='syear' value="${syear}"/>
                                 <c:param name='smonth' value="${smonth}"/>
                                 <c:param name='selectPet' value="${selectPet}"/>
                                 <c:param name='userId' value="${userId}"/>
                                 <c:param name='password' value="${password}"/>
                                 <c:param name='date' value="${currentDay}"/>
                              </c:url>" >
                     ${cPet}
                     </a>
                     
                     <% 
                        System.out.println(p.getName() +" " +list.contains(name));
                     }
                  }
               }
               System.out.println(list);
            }
         }
         out.print("</div>");
         out.print("</td>");
         if (cnt % 7 == 0) {
            out.print("</tr>");
            out.print("<tr align = \"center\" style=\"height:120px;\">");
         }
      }
      if (date >= 28) {
         while (cnt % 7 != 0) {
            out.print("<td align = \"center\" >");
            out.print("&nbsp");
            out.print("</td>");
            cnt++;
         }
         out.print("</tr>");
      }

      out.print("</table>");
      out.print("</div>");
      out.print("</form>");
   %>

</body>
</html>