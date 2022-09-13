package hr.algebra.futurama.dao

import android.content.Context

fun getFuturamaRepository(context: Context?) = FuturamaSqlHelper(context)