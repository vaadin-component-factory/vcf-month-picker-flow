# Month Picker
MonthPicker is a server-side Vaadin component built on top of the [`<vcf-month-picker>`](https://github.com/vaadin-component-factory/vcf-month-picker) web component. It allows users to select a `YearMonth` value through a month-selection overlay. 

This component is part of Vaadin Component Factory.

## Features

- Month & Year Picker with keyboard and mouse support
- Configurable Year Range via setMinYear / setMaxYear
- Localization: Custom month names, short labels, and input formats
- Read-only & Disabled modes
- Clear Button support
- Tooltip support
- Disable auto open
- Validation & Error Handling
- Accessible with proper ARIA attributes

Read more in the [web-component documentation](https://github.com/vaadin-component-factory/vcf-month-picker/blob/master/README.md).

## Running the component demo
Run from the command line:
- `mvn  -pl vcf-month-picker-flow-demo -Pwar install jetty:run`

Then navigate to `http://localhost:8080/`

## Installing the component
Run from the command line:
- `mvn clean install -DskipTests`

## Profiles
### Profile "directory"
This profile, when enabled, will create the zip file for uploading to Vaadin's directory

### Profile "production"
This profile, when enabled, will execute a production build for the demo

## Using the component in a Flow application
To use the component in an application using maven,
add the following dependency to your `pom.xml`:
```
<dependency>
    <groupId>org.vaadin.addons.componentfactory</groupId>
    <artifactId>vcf-month-picker-flow</artifactId>
    <version>${component.version}</version>
</dependency>
```

## How to Use

#### Basic use
```java
MonthPicker monthPicker = new MonthPicker();
monthPicker.setLabel("Month Picker");
monthPicker.setPlaceholder("Select a month");
monthPicker.setClearButtonVisible(true);
```

#### Value change listener
```java
picker.addValueChangeListener(e -> {
    String selected = e.getValue(); // e.g. "2025-04"
    // handle selection
});
```

#### Custom month names & formats
```java
picker.seti18n(new MonthPickerI18n()
    .setMonthNames(List.of("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                           "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"))
    .setMonthLabels(List.of("Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"))
    .setFormats("MM/YYYY", "MM-YYYY", "YYYY/MM", "MMYYYY"));
```

## Flow documentation
Documentation for Vaadin Flow can be found in [Flow documentation](https://vaadin.com/docs/latest/flow).

## License
Distributed under Apache Licence 2.0. 

### Sponsored development
Major pieces of development of this add-on has been sponsored by multiple customers of Vaadin. Read more about Expert on Demand at: [Support](https://vaadin.com/support) and [Pricing](https://vaadin.com/pricing).
