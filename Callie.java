package xyz.logicaldevz.Callie;

import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.Dates;
import com.google.appinventor.components.runtime.util.TimerInternal;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.common.*;
import com.google.appinventor.components.common.PropertyTypeConstants;


import java.util.*;
import java.util.*;

@DesignerComponent(
        version = 1,
        description = "Aix to make Calendar Views, Made by LogicalDevz team",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "")

@SimpleObject(external = true)
//Libraries
@UsesLibraries(libraries = "")
//Permissions
@UsesPermissions(permissionNames = "")

public class Callie extends AndroidNonvisibleComponent {
  private Context context;
  private CalendarView calendarView;
  private String myDate;
  private SimpleDateFormat sdf;
  private long timeInMillis;
  private Date date;

  public Callie(ComponentContainer container) {
    super(container.$form());
    context = container.$context();
    calendarView = new CalendarView(context);
  }

  @SimpleFunction
  public void CreateCalendar(AndroidViewComponent view) {
    LinearLayout linearLayout = new LinearLayout(context);
    linearLayout.addView(calendarView);
    ViewGroup viewGroup = (ViewGroup) view.getView();
    viewGroup.addView(linearLayout);
    calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
     @Override
     public void onSelectedDayChange (CalendarView view, int year, int month, int dayOfMonth){
      DayChanged(year, month, dayOfMonth);
     }
    });
  }
  @SimpleEvent
  public void DayChanged(int year, int month, int dayOfMonth) {
    EventDispatcher.dispatchEvent(this, "DayChanged", year, month, dayOfMonth);
  }
  @SimpleProperty
  public long Date(){
  return calendarView.getDate();
  }
  @SimpleProperty
  public int FirstDayOfWeek(){
      return calendarView.getFirstDayOfWeek();
  }
  @SimpleProperty
  public long MaxDate(){
  return calendarView.getMaxDate();
  }
  @SimpleProperty
  public long MinDate(){
  return calendarView.getMinDate();
  }
  @SimpleProperty
  public void date(long value){
      calendarView.setDate(value);
  }
  @SimpleProperty
  public void firstDayOfWeek(int value){
      calendarView.setFirstDayOfWeek(value);
  }
  @SimpleProperty
  public void maxDate(long value){
      calendarView.setMaxDate(value);
  }
  @SimpleProperty
  public void minDate(long value){
      calendarView.setMinDate(value);
  }
  @SimpleProperty
  public void setWeekNumberColor(int color){
      calendarView.setWeekNumberColor(color);
  }
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR, defaultValue = Component.DEFAULT_VALUE_COLOR_DEFAULT)
  @SimpleProperty
  public void WeekNumberColor(final int Color){
        calendarView.setWeekNumberColor(Color)
  }
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR, defaultValue = Component.DEFAULT_VALUE_COLOR_DEFAULT)
  @SimpleProperty
  public void WeekSeparatorLineColor(final int Color){
        calendarView.setWeekSeparatorLineColor(Color)
  }
@SimpleFunction()
  public long StringDateToMilliSeconds(String date){
    String[] dateArray = date.split("/");
    String year = dateArray[0];
    String month = dateArray[1];
    String day = dateArray[2];
    Calendar calendar = Calendar.getInstance();
    calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
    long milliSeconds = calendar.getTimeInMillis();
    return milliSeconds;
}
}
