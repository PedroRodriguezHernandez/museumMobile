package com.example.museummobile.core.supabase

import android.content.Context
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.auth.Auth


object SupabaseClient {

    private var client: SupabaseClient? = null

    fun getClient(context: Context): SupabaseClient {
        if (client == null) {
            createSupabaseClient(
                supabaseUrl =  "https://ixxpufbjinyviuodwxfl.supabase.co",
                supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Iml4eHB1ZmJqaW55dml1b2R3eGZsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDQ4MDE0MTMsImV4cCI6MjA2MDM3NzQxM30.p_KjP05f-xM0C487sc5whI6LHoiy_bUDgcABO2_Y_QI"
            ) {
                install(Auth)
                install(Postgrest)
            }
        }
        return client!!
    }
}