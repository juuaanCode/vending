# Vending

[![en](https://img.shields.io/badge/lang-en-red.svg)](/README.md)

Este proyecto es parte de la asignatura _Programación Orientada a Objetos_ de Ingeniería Informática en la Universidad de Valladolid. Se pedía implementar clases en Java para ayudar a una compañía ficticia en el sector de las máquinas de vending a gestionar sus sistemas.

Las [clases](/src/uva/poo/vending/) se relacionan de la siguiente manera: `VendingSystem` gestiona diferentes sedes `VendingCity`. En cada una de estas sedes hay múltiples `VendingMachine` que venden objetos de la clase abstracta `Vendible`. Estos _vendibles_ pueden ser `Pack` de varios produtcos o un solo `Product`. Se usó JavaDoc para la documentación detallada de cada clase.

La principal tarea, sin embargo, no era implementar estas clases sino los [tests](/src/uva/poo/tests/) de cada una ya que conseguir un porcentaje de cobertura alto era un requisito para aprobar la asignatura. Usamos JUnit para esta tarea.

Este proyecto fue realizado en colaboración con otro estudiante, Santiago Barbés.
