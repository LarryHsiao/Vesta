package com.silverhetch.vesta

import com.silverhetch.vesta.arch.database.Database
import com.silverhetch.vesta.database.H2DB
import com.silverhetch.vesta.tag.DBTags
import com.silverhetch.vesta.tag.Tags
import com.silverhetch.vesta.target.DBTargets
import com.silverhetch.vesta.target.Targets
import java.sql.Connection

class VestaImpl : Vesta {
    private val dbConnection: Connection by lazy {
        H2DB(System.getProperty("user.home")).connection()
    }

    override fun target(): Targets {
        return DBTargets(dbConnection)
    }

    override fun tag(): Tags {
        return DBTags(dbConnection)
    }
}