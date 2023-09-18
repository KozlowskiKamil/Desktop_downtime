# Desktop Fault Reporting Application - JavaFX

## Description

The Desktop Fault Reporting Application is a JavaFX-based tool that allows users to report faults through a graphical user interface. The application communicates with a server via a REST API, where the server checks the availability of a technician to handle the fault report. Upon confirming the technician's availability, the user can proceed to submit the fault report. The application automatically records the waiting time, fault occurrence time, and logs the start and end dates of the fault in the database.

## Features

- **Fault Reporting:** Users can input fault details, including descriptions and priorities, through the graphical interface.
- **Server Communication:** The application communicates with the server via a REST API to check technician availability.
- **Fault Confirmation:** Users can confirm the fault report after technician availability is confirmed.
- **Time Tracking:** The application records waiting time and fault occurrence time.
- **Database Logging:** Start and end dates of the fault are logged in the database.

## Getting Started

### Installation

1. Clone this repository: `git clone https://github.com/your/repository.git`
2. Navigate to the project directory: `cd your-project-directory`
3. Build and run the application: `./mvnw javafx:run`

### Usage

1. Launch the application.
2. Fill in fault details through the graphical interface.
3. Click the "Submit" button to submit the fault report.
4. The application will communicate with the server to check technician availability.
5. If a technician is available, the fault report can be confirmed.

## Database

The application uses a database to store fault information, including start and end dates.

## Contact

If you have any questions or feedback, feel free to contact us at [Kozlowski Kamil](mailto:kozlowski.kamil2k@gmail.com).

Thank you for using the Desktop Fault Reporting Application!
