package hr.algebra.futurama

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import hr.algebra.futurama.dao.FuturamaRepository
import hr.algebra.futurama.dao.getFuturamaRepository
import hr.algebra.futurama.model.Item

private const val AUTHORITY = "hr.algebra.futurama.api.provider"
private const val PATH = "items"

val FUTURAMA_PROVIDER_URI = Uri.parse("content://$AUTHORITY/$PATH")

private const val ITEMS = 10
private const val ITEM_ID = 20

private val URI_MATCHER = with(UriMatcher(UriMatcher.NO_MATCH)) {
    addURI(AUTHORITY, PATH, ITEMS)
    addURI(AUTHORITY, "$PATH/#", ITEMS)
    this
}

class FuturamaProvider : ContentProvider() {

    private lateinit var futuramaRepository: FuturamaRepository

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        when (URI_MATCHER.match(uri)) {
            ITEMS -> return futuramaRepository.delete(selection, selectionArgs)
            ITEM_ID -> {
                uri.lastPathSegment?.let {
                    return futuramaRepository.delete("${Item::_id.name}", arrayOf(it))
                }

            }
        }

        throw IllegalArgumentException("Wrong uri")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = futuramaRepository.insert(values)
        return ContentUris.withAppendedId(FUTURAMA_PROVIDER_URI, id)
    }

    override fun onCreate(): Boolean {
        futuramaRepository = getFuturamaRepository(context)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? = futuramaRepository.query(projection, selection, selectionArgs, sortOrder)

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        when (URI_MATCHER.match(uri)) {
            ITEMS -> return futuramaRepository.update(values, selection, selectionArgs)
            ITEM_ID -> {
                uri.lastPathSegment?.let {
                    return futuramaRepository.update(values, "${Item::_id.name}", arrayOf(it))
                }
            }
        }
        throw IllegalArgumentException("Wrong uri")
    }
}