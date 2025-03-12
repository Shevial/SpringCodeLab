# README - Notaciones de Relaciones en JPA (Ejemplo Sencillo)

## Ejemplo Central: Sistema de Biblioteca

Entidades principales:
- **Book**: Representa un libro
- **Category**: Categoría de libros
- **BookDetail**: Detalles específicos de un libro
- **Author**: Autores de libros

---

## Relaciones Básicas en JPA

### @ManyToOne
**Concepto:**  
Múltiples instancias de la clase actual se asocian a una única instancia de otra entidad.

**Ejemplo en clase Book:**
```java
@Entity
public class Book {
    @Id
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "category_id")  // Columna FK en tabla Book
    private Category category;
}
 ```

#### Explicación:

**"Many"** se refiere a los libros

**"One"** se refiere a la categoría

Cada libro pertenece a UNA categoría y muchos libros pueden compartir la misma categoría

---

### **@OneToMany**
**Concepto:**  
Una entidad contiene una colección de otras entidades relacionadas.

**Ejemplo en clase Category:**

``` java

@Entity
public class Category {
    @Id
    private Long id;
    
    @OneToMany(mappedBy = "category")
    private List<Book> books = new ArrayList<>();
}
```

#### Explicación:

Una categoría puede contener MUCHOS libros

Relación inversa de @ManyToOne

**mappedBy** indica que la relación se gestiona desde el lado de Book

---

### **@OneToOne**
**Concepto:**  

Relación exclusiva 1:1 entre dos entidades.

#### Ejemplo en clases Book y BookDetail:

``` java

@Entity
public class Book {
    @Id
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "detail_id")  // FK en tabla Book
    private BookDetail detail;
}


@Entity
public class BookDetail {
    @Id
    private Long id;
    private Integer pages;
    private String summary;
}
``` 

#### Explicación:

Cada libro tiene UN único detalle

Cada detalle pertenece a UN único libro

---


### @ManyToMany
**Concepto:**
Relación bidireccional donde ambas entidades pueden tener múltiples asociaciones.

### Ejemplo en clases Book y Author:

``` java

@Entity
public class Book {
    @Id
    private Long id;
    
    @ManyToMany
    @JoinTable(
        name = "book_author",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();
}

@Entity
public class Author {
    @Id
    private Long id;
    
    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();
}
```

#### Explicación:

Tabla intermedia: book_author

joinColumns: FK de la entidad actual

inverseJoinColumns: FK de la entidad relacionada

---

## Anotaciones Complementarias
### @JoinColumn

Especifica la columna de unión para relaciones.

Uso típico:

``` java

@ManyToOne
@JoinColumn(
    name = "publisher_id",         // Nombre columna FK
    referencedColumnName = "id",   // Columna referenciada
    nullable = false
)
private Publisher publisher;
```

#### Explicación:

**name:** Nombre de la columna FK

**referencedColumnName:** Columna objetivo en la tabla relacionada

**nullable:** Permite valores nulos

---

### Resumen Rápido de Relaciones
**@ManyToOne:**
"Many": La clase actual, por ejemplo, Book.
"One": El atributo referenciado, por ejemplo, Category.

**@OneToMany:**
"One": La clase actual, por ejemplo, Category.
"Many": La colección de la entidad relacionada, por ejemplo, una lista de Book.

**@OneToOne:**
Cada Book se asocia de forma exclusiva con un BookDetail.

**@ManyToMany:**
Un Book puede tener varios Author y viceversa.

**@JoinColumn:**
Define la columna de unión (clave foránea) entre entidades (por ejemplo, category_id en Book).

---

Best Practices:

Usar mappedBy en relaciones bidireccionales

Inicializar colecciones (new ArrayList<>())

Preferir relaciones unidireccionales cuando sea posible

Usar @JoinTable para personalizar tablas intermedias
