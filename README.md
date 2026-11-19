# MedTrack Backend

MedTrack is a healthcare and clinical-practice management backend. This repository contains the foundational domain skeleton for Group 3 of the Java backend internship.

## Technology

- Java 21
- Spring Boot 3.4.3
- Spring Data JPA / Hibernate
- Jakarta Validation
- Apache Maven
- PostgreSQL

## Architecture

The project uses a package-by-feature architecture under `com.coderaah.medtrack`. Each feature groups its domain model and the packages reserved for its application layers.

> At this stage, only the `domain` packages contain implementations, while `dto`, `repository`, `service`, and `controller` packages serve as committed placeholders for future tickets.

- `identity` contains `Person`, `UserAccountStatus`, `Role`, `UserAccount`, `UserRole`.
- `patient` contains `BloodType`, `AllergySeverity`, `AllergyStatus`, `PatientConditionStatus`, `PatientProfile`, `PatientAllergy`, `PatientCondition`.
- `doctor` contains `DoctorRelationshipType`, `ScheduleExceptionType`, `ScheduleExceptionReason`, `DoctorProfile`, `Specialty`, `DoctorSpecialty`, `PatientDoctorRelationship`, `DoctorAvailabilityRule`, `DoctorScheduleException`.
- `appointment` contains `AppointmentStatus`, `AppointmentType`, `Appointment`, `AppointmentStatusHistory`.
- `visit` contains `VisitStatus`, `MedicalReportType`, `Visit`, `MedicalReport`.
- `medication` contains `DosageForm`, `Medication`.
- `prescription` contains `PrescriptionStatus`, `Prescription`, `PrescriptionItem`, `PrescriptionStatusHistory`.
- `pharmacy` contains `PharmacyStaffRole`, `InventoryMovementType`, `PrescriptionSubmissionStatus`, `PrescriptionFulfillmentStatus`, `Pharmacy`, `PharmacyStaffMembership`, `PharmacyInventory`, `InventoryMovement`, `PrescriptionSubmission`, `PrescriptionFulfillment`, `PrescriptionFulfillmentItem`.
- `notification` contains `NotificationType`, `NotificationStatus`, `Notification`.
- `common.audit` contains `AuditLog`, the shared audit entity.

`common.config`, `common.exception`, and `security` are also package placeholders.

## Build

With Java 21, compile the project using the Maven Wrapper.

```bash
./mvnw clean compile
```
