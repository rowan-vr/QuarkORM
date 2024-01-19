package dev.rvr.quark.commons.models

import com.google.common.base.Objects

class QuarkSchema private constructor(builder: Builder){
    val models: Set<QuarkModel> = builder.models

    override fun equals(other: Any?): Boolean {
        return other is QuarkSchema && other.models == models
    }

    override fun hashCode(): Int {
        return Objects.hashCode(models)
    }

    override fun toString(): String {
        return "QuarkSchema(models=$models)"
    }

    class Builder {
        internal var models: Set<QuarkModel> = emptySet()

        fun model(field: QuarkModel): Builder {
            models = models + field
            return this
        }

        fun build(): QuarkSchema {
            return QuarkSchema(this)
        }
    }
}