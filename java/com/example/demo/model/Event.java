package com.example.demo.model;

/**
 * FullCalendar用Event Object
 * 
 * @see https://fullcalendar.io/docs/event_data/Event_Object/
 */
public class Event {

	private String title;

	/**
	 * カレンダーの開始日付
	 */
	private String start;

	/**
	 * カレンダーの終了日付
	 */
	private String end;

	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}