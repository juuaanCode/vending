# Vending

[![es](https://img.shields.io/badge/lang-es-red.svg)](/README-ES.md)

This project is part of the subject _Programación Orientada a Objetos_ (Object-oriented programming) of Computer Science at the University of Valladolid. We were asked to implement classes in Java to help a (fictitious) company in the vending machine business manage their systems.

The [classes](/src/uva/poo/vending/) are related as follows: `VendingSystem` manages several `VendingCity`. In each `VendingCity` there are multiple `VendingMachine` which sell objects of the abstract class `Vendible`. This _vendibles_ can be either a `Pack` containing several products or a single `Product`. JavaDoc was used for the extensive documentation of each class.

But the main task was not the implementation of these classes. The focus was on the [tests](/src/uva/poo/tests/) for each class as achieving a high coverage percentage was a requisite to pass the subject. We used JUnit for this tests.

This project was developed in collaboration with another student, Santiago Barbés, and in Spanish.
