/*
 * Copyright 2025 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.vaadin.addons.componentfactory.demo;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;
import java.time.YearMonth;
import java.util.Arrays;
import org.vaadin.addons.componentfactory.monthpicker.MonthPicker;
import org.vaadin.addons.componentfactory.monthpicker.MonthPicker.MonthPickerI18n;

/**
 * View for {@link MonthPicker} demo.
 *
 * @author Vaadin Ltd
 */
@SuppressWarnings("serial")
@Route("")
public class MonthPickerDemoView extends DemoView {

  @Override
  public void initView() {
    createBasicMonthPickerDemo();
    createAutoOpenDisabledMonthPickerDemo();
    createMinMaxYearMonthPickerDemo();
    createInvalidValueMonthPickerDemo();
    createReadOnlyAndDisabledDemo();
    createInternalizedMonthPickerDemo();

    addCard("Additional code used in the demo", new Span("These methods are used in the demo."));
  }

  private void createBasicMonthPickerDemo() {
    Div message = createMessageDiv("basic-month-picker-demo-message");

    // begin-source-example
    // source-example-heading: Basic month picker
    MonthPicker monthPicker = new MonthPicker();
    monthPicker.setLabel("Month Picker");
    monthPicker.setPlaceholder("Select a month");
    monthPicker.setClearButtonVisible(true);
    monthPicker.addValueChangeListener(ev -> {
      updateMessage(message, monthPicker);
    });
    // end-source-example

    monthPicker.setId("basic-month-picker");

    addCard("Basic month picker", monthPicker, message);
  }

  private void createAutoOpenDisabledMonthPickerDemo() {
    Div message = createMessageDiv("auto-open-disabled-month-picker-demo-message");

    // begin-source-example
    // source-example-heading: With auto open disabled
    MonthPicker monthPicker = new MonthPicker();
    monthPicker.setLabel("Month Picker");
    monthPicker.setPlaceholder("Select a month");
    monthPicker.setAutoOpen(false);
    monthPicker.setClearButtonVisible(true);
    monthPicker.addValueChangeListener(ev -> {
      updateMessage(message, monthPicker);
    });
    // end-source-example

    monthPicker.setId("auto-open-disabled-month-picker");

    addCard("With auto open disabled", monthPicker, message);
  }

  private void createMinMaxYearMonthPickerDemo() {
    Div message = createMessageDiv("min-max-year-month-picker-demo-message");

    // begin-source-example
    // source-example-heading: With min and max year
    MonthPicker monthPicker = new MonthPicker();
    monthPicker.setLabel("Month Picker");
    monthPicker.setPlaceholder("Select a month");
    monthPicker.setMaxYear(2026);
    monthPicker.setMinYear(2020);
    monthPicker.setHelperText("Min year: 2020 & Max year: 2026");
    monthPicker.setClearButtonVisible(true);
    monthPicker.addValueChangeListener(ev -> {
      updateMessage(message, monthPicker);
    });
    // end-source-example

    monthPicker.setId("min-max-year-month-picker");

    addCard("With min and max year", monthPicker, message);
  }

  private void createInvalidValueMonthPickerDemo() {
    Div message = createMessageDiv("invalid-value-month-picker-demo-message");

    // begin-source-example
    // source-example-heading: With invalid value
    MonthPicker monthPicker = new MonthPicker();
    monthPicker.setLabel("Month Picker");
    monthPicker.setPlaceholder("Select a month");
    monthPicker.setMaxYear(2026);
    monthPicker.setMinYear(2020);
    monthPicker.setValue(YearMonth.of(2019, 6));
    monthPicker.setErrorMessage("Month is outside of year range");
    monthPicker.setHelperText(
        "Min year: 2020 & Max year: 2026. If you enter a date outside that range, an error message will be shown.");
    monthPicker.setClearButtonVisible(true);
    monthPicker.addValueChangeListener(ev -> {
      updateMessage(message, monthPicker);
    });
    // end-source-example

    monthPicker.setId("invalid-value-month-picker");

    addCard("With invalid value", monthPicker, message);
  }

  private void createReadOnlyAndDisabledDemo() {
    // begin-source-example
    // source-example-heading: Read-Only & Disabled
    MonthPicker readonlyMonthPicker = new MonthPicker();
    readonlyMonthPicker.setLabel("Readonly");
    readonlyMonthPicker.setValue(YearMonth.of(2019, 6));
    readonlyMonthPicker.setReadOnly(true);

    MonthPicker disabledMonthPicker = new MonthPicker();
    disabledMonthPicker.setLabel("Disabled");
    disabledMonthPicker.setValue(YearMonth.of(2019, 6));
    disabledMonthPicker.setEnabled(false);

    // end-source-example

    readonlyMonthPicker.setId("readonly-month-picker");
    disabledMonthPicker.setId("disabled-month-picker");

    addCard("Read-Only & Disabled", readonlyMonthPicker, disabledMonthPicker);
  }

  private void createInternalizedMonthPickerDemo() {
    Div message = createMessageDiv("internalized-month-picker-demo-message");

    // begin-source-example
    // source-example-heading: With Custom Months and Formats
    MonthPicker monthPicker = new MonthPicker();
    monthPicker.setLabel("Month Picker");
    monthPicker.setPlaceholder("Select a month");
    monthPicker.setClearButtonVisible(true);

    MonthPickerI18n i18n = new MonthPickerI18n();
    i18n.setFormats("MM.YYYY", "MM/YYYY", "MMYYYY", "MM-YYYY", "YYYY.MM", "MM YYYY");
    i18n.setMonthNames(Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
        "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"));
    i18n.setMonthLabels(Arrays.asList("Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep",
        "Oct", "Nov", "Dic"));

    monthPicker.seti18n(i18n);
    monthPicker.setHelperText(
        "Spanish months labels/names and formats: 'MM.YYYY', 'MM/YYYY', 'MMYYYY', 'MM-YYYY', 'YYYY.MM', 'MM YYYY'");

    monthPicker.addValueChangeListener(ev -> {
      updateMessage(message, monthPicker);
    });
    // end-source-example

    monthPicker.setId("internalized-month-picker");

    addCard("With Custom Months and Formats", monthPicker, message);
  }

  // begin-source-example
  // source-example-heading: Additional code used in the demo
  /**
   * Additional code used in the demo
   */
  private void updateMessage(Div message, MonthPicker monthPicker) {
    message.setText("Selected year-month: " + monthPicker.getValue());
  }

  private Div createMessageDiv(String id) {
    Div message = new Div();
    message.setId(id);
    message.getStyle().set("whiteSpace", "pre");
    return message;
  }
  // end-source-example
}
