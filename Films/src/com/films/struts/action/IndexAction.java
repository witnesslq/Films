/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.films.struts.action;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.films.service.inter.IFCommService;
import com.films.service.inter.IFilmService;
import com.films.service.inter.ITimeTableService;

/** 
 * MyEclipse Struts
 * Creation date: 10-29-2012
 * 
 * XDoclet definition:
 * @struts.action parameter="flag"
 */
public class IndexAction extends DispatchAction {


	private IFilmService filmService;
	private ITimeTableService timeTableService;
	private IFCommService fcService;
	
	public void setFilmService(IFilmService filmService) {
		this.filmService = filmService;
	}
	public void setTimeTableService(ITimeTableService timeTableService) {
		this.timeTableService = timeTableService;
	}
	
	public void setFcService(IFCommService fcService) {
		this.fcService = fcService;
	}
	
	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		//set today movie to attribute
		request.getSession().setAttribute("quicktt",timeTableService.getTimetableToday(sdf.format(date)));
		request.getSession().setAttribute("sidecom", fcService.getAllCom());
		request.setAttribute("film", filmService.getUpcoming());
		request.setAttribute("tt", timeTableService.showTimeTable(8, 1));
		return mapping.findForward("index");
	}
	
	public ActionForward filmIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.setAttribute("tt", timeTableService.getTimeTable());
		return mapping.findForward("filmIndex");
	}
	
	public ActionForward upcoming(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.setAttribute("film", filmService.getUpcoming());
		return mapping.findForward("upcoming");
	}
	
	@SuppressWarnings("static-access")
	public ActionForward time(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		//set today movie to attribute
		request.setAttribute("tt",timeTableService.getTimetableToday(sdf.format(date)));
		
		//set tommorow movie to attribute
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,1);
		date=calendar.getTime();
		request.setAttribute("mtt",timeTableService.getTimetableToday(sdf.format(date)));
		return mapping.findForward("time");
	}
	
	public ActionForward help(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("goHelp");
	}
}