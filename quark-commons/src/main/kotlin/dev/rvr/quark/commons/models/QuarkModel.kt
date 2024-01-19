package dev.rvr.quark.commons.models

import com.google.common.base.Objects


class QuarkModel constructor(builder: Builder) {
    val name = builder.name!!
    val fields: Set<QuarkModelField> = builder.fields


    override fun equals(other: Any?): Boolean {
        return other is QuarkModel && other.name == name && other.fields == fields
    }

    override fun hashCode(): Int {
        return Objects.hashCode(fields,name)
    }

    override fun toString(): String {
        return "QuarkModel(name='$name', fields=$fields)"
    }

    class Builder {
        internal var fields: Set<QuarkModelField> = emptySet()
        internal var name: String? = null

        fun field(field: QuarkModelField): Builder {
            fields = fields + field
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun build(): QuarkModel {
            if (name == null) throw IllegalArgumentException("Model name cannot be null")
            return QuarkModel(this)
        }
    }
}