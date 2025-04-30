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
package org.vaadin.addons.componentfactory.monthpicker;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.shared.HasAutoOpen;
import com.vaadin.flow.component.shared.HasClearButton;
import com.vaadin.flow.component.shared.HasTooltip;
import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.shared.Registration;
import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;
import jakarta.annotation.Nullable;


/**
 * Server-side component for the <code>vcf-month-picker</code> element.
 * <p>
 * The Month Picker component is a calendar-like field that allows users to select a month within a
 * specific year.
 * </p>
 * 
 * <p>
 * This component can be configured with localization options and custom formats for parsing and
 * displaying the selected year-month.
 * </p>
 * 
 * @see MonthPickerI18n
 */
@SuppressWarnings("serial")
@Tag("vcf-month-picker")
@NpmPackage(value = "@vaadin-component-factory/vcf-month-picker", version = "1.1.0")
@JsModule("@vaadin-component-factory/vcf-month-picker/dist/src/vcf-month-picker.js")
public class MonthPicker extends AbstractSinglePropertyField<MonthPicker, YearMonth>
    implements HasLabel, HasAutoOpen, HasClearButton, HasPlaceholder, HasHelper, HasValidation,
    HasTooltip, Focusable<MonthPicker> {

  private final static SerializableFunction<String, YearMonth> PARSER = s -> {
    return s == null || s.isEmpty() ? null : YearMonth.parse(s);
  };

  private final static SerializableFunction<YearMonth, String> FORMATTER = ym -> {
    return ym == null ? "" : ym.toString();
  };

  private MonthPickerI18n i18n;

  /**
   * Creates an empty MonthPicker instance with no initial value.
   */
  public MonthPicker() {
    this(null);
  }

  /**
   * Creates a MonthPicker with the given initial value.
   *
   * @param initialYearMonth the initial selected {@link YearMonth}
   */
  public MonthPicker(YearMonth initialYearMonth) {
    super("value", initialYearMonth, String.class, PARSER, FORMATTER);
  }

  @Override
  public void setErrorMessage(String errorMessage) {
    getElement().setProperty("errorMessage", errorMessage);
  }

  @Override
  public String getErrorMessage() {
    return getElement().getProperty("errorMessage", "");
  }

  @Override
  public void setInvalid(boolean invalid) {
    getElement().setProperty("invalid", invalid);
    if (!invalid) {
      getElement().setProperty("errorMessage", "");
    }
  }

  @Override
  public boolean isInvalid() {
    return getElement().getProperty("invalid", false);
  }

  /**
   * Sets the minimum selectable year in the month picker.
   *
   * @param minYear the minimum year
   */
  public void setMinYear(int minYear) {
    getElement().setProperty("minYear", String.valueOf(minYear));
  }

  /**
   * Gets the minimum selectable year.
   *
   * @return the minimum year
   */
  public int getMinYear() {
    return Optional.ofNullable(getElement().getProperty("minYear")).map(Integer::valueOf).orElse(0);
  }

  /**
   * Sets the maximum selectable year in the month picker.
   *
   * @param maxYear the maximum year
   */
  public void setMaxYear(int maxYear) {
    getElement().setProperty("maxYear", String.valueOf(maxYear));
  }

  /**
   * Gets the maximum selectable year.
   *
   * @return the maximum year
   */
  public int getMaxYear() {
    return Optional.ofNullable(getElement().getProperty("maxYear")).map(Integer::valueOf).orElse(0);
  }

  /**
   * Returns the current internationalization settings used by the month picker.
   *
   * @return the {@link MonthPickerI18n} settings
   */
  public MonthPickerI18n getI18n() {
    return i18n;
  }

  /**
   * Sets the internationalization (i18n) properties for this month picker.
   *
   * @param i18n the {@link MonthPickerI18n} object with translation settings (must not be
   *        {@code null})
   */
  public void seti18n(MonthPickerI18n i18n) {
    this.i18n = Objects.requireNonNull(i18n, "The i18n properties object should not be null");
    JsonObject i18nJson = this.getI18nJsonObject(i18n);
    this.getElement().setPropertyJson("i18n", i18nJson);
  }

  private JsonObject getI18nJsonObject(MonthPickerI18n i18n) {
    JsonObject i18nJson = Json.createObject();

    // monthNames
    writeStringListAsJsonArray(i18n.getMonthNames(), i18nJson, "monthNames");

    // short month names (used for MMM parsing)
    writeStringListAsJsonArray(i18n.getShortMonthNames(), i18nJson, "shortMonthNames");

    // monthLabels
    writeStringListAsJsonArray(i18n.getMonthLabels(), i18nJson, "monthLabels");

    // formats
    writeStringListAsJsonArray(i18n.getFormats(), i18nJson, "formats");

    return i18nJson;
  }

  /**
   * Converts the given list of strings into a json array. That json array is then written into the given
   * json object at the given json property.
   * <p/>
   * When passing null for the list parameter, the method is noop.
   * @param stringList list of strings to convert
   * @param jsonObject target json object
   * @param jsonProperty target json object property
   */
  private static void writeStringListAsJsonArray(@Nullable List<String> stringList, JsonObject jsonObject, String jsonProperty) {
    if (stringList != null) { // TODO might make sense to check for empty lists, too?
      JsonArray monthNames = Json.createArray();
      for (int i = 0; i < stringList.size(); i++) {
        monthNames.set(i, stringList.get(i));
      }
      jsonObject.put(jsonProperty, monthNames);
    }
  }

  /**
   * A class that contains internationalization settings for the {@link MonthPicker} component.
   * <p>
   * This allows customizing month names, labels, and parsing/formatting patterns for the value
   * input field.
   */
  public static class MonthPickerI18n implements Serializable {
    private List<String> monthNames;
    private List<String> monthLabels;
    private List<String> shortMonthNames;
    private List<String> formats;

    /**
     * Gets the name of the months.
     *
     * @return the month names
     */
    public List<String> getMonthNames() {
      return monthNames;
    }

    /**
     * Sets the name of the months, starting from January and ending on December. These are used for parsing and
     * formatting, when the long month name pattern ("MMMM") is used.
     *
     *
     * @param monthNames the month names
     * @return this instance for method chaining
     */
    public MonthPickerI18n setMonthNames(List<String> monthNames) {
      this.monthNames = monthNames;
      return this;
    }

    /**
     * Gets the labels of the months.
     *
     * @return the month labels
     */
    public List<String> getMonthLabels() {
      return monthLabels;
    }

    /**
     * Sets the labels of the months, to be shown in the calendar overlay.
     *
     * @param monthLabels the month labels
     * @return this instance for method chaining
     */
    public MonthPickerI18n setMonthLabels(List<String> monthLabels) {
      this.monthLabels = monthLabels;
      return this;
    }

    /**
     * Gets the short labels of the months.
     *
     * @return the short month labels
     */
    public List<String> getShortMonthNames() {
      return shortMonthNames;
    }

    /**
     * Sets the short names of the months. These are used for parsing and formatting, when the short month
     * name pattern ("MMM") is used.
     *
     * @param shortMonthNames the short month names
     * @return this instance for method chaining
     */
    public MonthPickerI18n setShortMonthNames(List<String> shortMonthNames) {
      this.shortMonthNames = shortMonthNames;
      return this;
    }

    /**
     * Get the list of custom formats that are used for formatting the year-month displayed in the
     * text field, and for parsing the user input
     *
     * @return list of date patterns or null
     */
    public List<String> getFormats() {
      return formats;
    }

    /**
     * Sets a single format pattern to be used for both displaying and parsing values.
     * <p/>
     * <ul>
     *   <li>{@code YY} - 2-digit year</li>
     *   <li>{@code YYYY} - 4-digit year</li>
     *   <li>{@code M} - 1- or 2-digit month</li>
     *   <li>{@code MM} - 2-digit month (with leading zero)</li>
     *   <li>{@code MMM} - short month name (requires the i18n short month names to be set)</li>
     *   <li>{@code MMMM} - long month name (requires the i18n month names to be set)</li>
     * </ul>
     *
     * @param format a string pattern using year-month symbols (e.g., {@code "MM-YYYY"})
     * @return this instance for method chaining
     * @see #setFormats(String, String...)
     */
    public MonthPickerI18n setFormat(String format) {
      this.setFormats(format);
      return this;
    }

    /**
     * Sets a primary format and additional parsing formats for interpreting user input.
     * <p>
     * The primary format is used for both formatting and parsing. If parsing fails using the
     * primary format, additional formats are used in order.
     * </p>
     *
     * <p>Format patterns may include:
     * <ul>
     *   <li>{@code YY} - 2-digit year</li>
     *   <li>{@code YYYY} - 4-digit year</li>
     *   <li>{@code M} - 1- or 2-digit month</li>
     *   <li>{@code MM} - 2-digit month (with leading zero)</li>
     *   <li>{@code MMM} - short month name (requires the i18n short month names to be set)</li>
     *   <li>{@code MMMM} - long month name (requires the i18n month names to be set)</li>
     * </ul>
     * </p>
     *
     * @param primaryFormat the main format used for formatting and parsing
     * @param additionalParsingFormats fallback formats used for parsing only
     * @return this instance for method chaining
     */
    public MonthPickerI18n setFormats(String primaryFormat, String... additionalParsingFormats) {
      Objects.requireNonNull(additionalParsingFormats,
          "Additional parsing formats must not be null");

      if (primaryFormat == null) {
        this.formats = null;
      } else {
        this.formats = new ArrayList<>();
        this.formats.add(primaryFormat);
        this.formats.addAll(Stream.of(additionalParsingFormats).filter(Objects::nonNull)
            .collect(Collectors.toList()));
      }

      return this;
    }
  }


  /**
   * Returns whether the month picker overlay is currently open.
   * <p>
   * This property is synchronized from the client when the {@code vcf-month-picker-opened-changed} event occurs.
   *
   * @return {@code true} if open, {@code false} otherwise
   */
  @Synchronize(property = "opened", value = "vcf-month-picker-opened-changed")
  public boolean isOpened() {
    return getElement().getProperty("opened", false);
  }

  /**
   * Event fired when the opened state of the overlay changes.
   */
  @DomEvent("vcf-month-picker-opened-changed")
  public static class OpenedChangeEvent extends ComponentEvent<MonthPicker> {
    private final boolean opened;

    public OpenedChangeEvent(MonthPicker source, boolean fromClient) {
      super(source, fromClient);
      this.opened = source.isOpened();
    }

    public boolean isOpened() {
      return opened;
    }
  }

  /**
   * Adds a listener for {@code vcf-month-picker-opened-changed} events triggered when the overlay is opened or closed.
   *
   * @param listener the event listener
   * @return a {@link Registration} for removing the listener
   */
  public Registration addOpenedChangeListener(ComponentEventListener<OpenedChangeEvent> listener) {
    return addListener(OpenedChangeEvent.class, listener);
  }

}
