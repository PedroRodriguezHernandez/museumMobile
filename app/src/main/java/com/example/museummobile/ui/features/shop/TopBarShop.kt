package com.example.museummobile.ui.features.shop

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCartCheckout
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.museummobile.R
import com.example.museummobile.core.model.Museum
import com.example.museummobile.ui.viewModels.SelectDate
import com.example.museummobile.ui.viewModels.SharedViewModel
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun TopBarShop(
    selectedDateMillis: Long?,
    number: Int,
    sharedViewModel: SharedViewModel,
    navController: NavController,
    selectedList: MutableList<SelectDate>,
    function: () -> Unit,
    museums: List<Museum>,
    selectedMuseum: Museum?,
    onMuseumSelected: (Museum) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = colorResource(R.color.light_blue),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { function() }
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.blue),
                text = selectedDateMillis?.let {
                    Instant.fromEpochMilliseconds(it)
                        .toLocalDateTime(TimeZone.currentSystemDefault())
                        .date.toString()
                } ?: stringResource(R.string.select_date),
            )
        }

        Box(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = colorResource(R.color.light_blue),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { expanded = true }
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = selectedMuseum?.name ?: stringResource(R.string.select_museum),
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.blue)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                museums.forEach { museum ->
                    DropdownMenuItem(
                        text = { Text(museum.name) },
                        onClick = {
                            onMuseumSelected(museum)
                            expanded = false
                        }
                    )
                }
            }
        }
        if (number > 0) {
            IconButton(onClick = {
                val selectedDate = selectedDateMillis?.let {
                    Instant.fromEpochMilliseconds(it)
                        .toLocalDateTime(TimeZone.currentSystemDefault()).date
                }

                selectedDate?.let { currentDate ->
                    val updatedList = sharedViewModel.selectDates.value
                        .filterNot { it.date == currentDate && selectedList.any { sel -> sel.id == it.id } }

                    sharedViewModel.clearDates()
                    sharedViewModel.addDates(updatedList + selectedList)
                }

                navController.navigate("cart")
            }) {
                BadgedBox(badge = {
                    if (number > 0) {
                        Badge { Text("") }
                    }
                }) {
                    Icon(
                        Icons.Outlined.ShoppingCartCheckout,
                        contentDescription = stringResource(R.string.shopping_checkout),
                        tint = colorResource(R.color.blue)
                    )
                }
            }
        }
    }
}