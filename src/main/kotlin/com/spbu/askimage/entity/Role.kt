package com.spbu.askimage.entity

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import javax.persistence.Entity
import javax.persistence.GenerationType
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@NoArgsConstructor
@AllArgsConstructor
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String? = null

    override fun toString(): String {
        return "Role(id=$id, name=$name)"
    }
}