package dev.rvr.quark.commons.models

import com.google.common.base.Objects

class QuarkModelField constructor(builder: Builder) {
    val name: String = builder.name
    val type: String = builder.type

    override fun equals(other: Any?): Boolean {
        return other is QuarkModelField && other.name == name && other.type == type
    }

    override fun hashCode(): Int {
        return Objects.hashCode(name,type)
    }

    override fun toString(): String {
        return "QuarkModelField(name='$name', type='$type')"
    }

    class Builder {
        internal var name: String = ""
        internal var type: String = ""

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun type(type: String): Builder {
            this.type = type
            return this
        }

        fun build(): QuarkModelField {
            return QuarkModelField(this)
        }
    }
}