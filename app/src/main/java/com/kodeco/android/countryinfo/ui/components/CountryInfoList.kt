package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodeco.android.countryinfo.flow.Flows
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.sample.sampleCountries

@Composable
fun CountryInfoList(
    countries: List<Country>,
    onRefreshClick: () -> Unit,
) {
    var selectedCountry: Country? by remember { mutableStateOf(null) }

    Column {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Taps : ${Flows.tapFlow.value ?: 0}",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
            )
            Button(
                onClick = { onRefreshClick() }
            ) {
                Text(
                    text = "Refresh",
                    fontSize = 20.sp
                )

            }
            Text(
                text = "Back : ${Flows.backFlow.value ?: 0}",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
            )
        }

        selectedCountry?.let { country ->
            CountryDetailsScreen(country = country) {
                selectedCountry = null
                Flows.tapBack()
            }
        } ?: run {
            LazyColumn {
                items(countries) { country ->
                    CountryInfoRow(country) {
                        selectedCountry = country
                        Flows.tap()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CountryInfoListPreview() {
    CountryInfoList(
        countries = sampleCountries,
        onRefreshClick = {},
    )
}
