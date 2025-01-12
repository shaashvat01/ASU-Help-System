# CSE 360 Help System

## Overview
The **CSE 360 Help System** is an adaptive learning support platform designed to aid students, instructors, and administrators in the CSE 360 course at Arizona State University. This system provides secure, role-based assistance tailored to individual user needs, enhancing accessibility and personalization in a diverse learning environment.

## Features
### Core Functionalities
- **Secure Identity Management**: First-time admin setup, secure account creation, and password management.
- **Role-Based Access Control**: Three primary roles—Admin, Instructor, and Student—with unique access rights.

### Content Management
- Create, update, and delete help articles with detailed fields (title, description, keywords, and references).
- Group articles into categories for better organization.
- Search and filter articles by keywords, groups, and content level (e.g., Beginner, Advanced).

### Security
- Encryption for sensitive article content to ensure secure storage and retrieval.
- Backup and restore options with support for encrypted content.
- Special access groups to manage proprietary or sensitive articles.

### User-Specific Features
- **Students**: Search, filter, and view relevant articles based on permissions.
- **Instructors**: Manage articles and groups, provide feedback, and assign access rights.
- **Admins**: Manage users, roles, articles, and special access groups.

## Architecture
The system follows a modular design with distinct components for user management, article handling, and security:
- **User Class**: Core information for Students, Instructors, and Admins.
- **Encryption Module**: Ensures sensitive data is securely encrypted.
- **Article and Group Classes**: Organize and manage help content.
- **Access Control**: Role-based permissions and group-level access.

## Requirements
### Prerequisites
- **Java**: JDK 8 or above.
- **JavaFX**: Required for the user interface.
- **Maven**: For dependency management.

Developed as a semester project for **CSE 360: Software Engineering** at Arizona State University.
