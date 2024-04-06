package com.kabe.techexam.features.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kabe.techexam.R
import com.kabe.techexam.domain.RandomPerson
import com.kabe.techexam.ui.theme.Black
import com.kabe.techexam.ui.theme.spacing
import com.kabe.techexam.util.DateUtil
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    randomPerson: RandomPerson
) {
    DetailScreenView(
        navigator = navigator,
        randomPerson = randomPerson
    )
}

@Composable
fun DetailScreenView(
    navigator: DestinationsNavigator,
    randomPerson: RandomPerson)
{

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (
            btnBack,
            imgProfile,
            txtName,
            txtBirthday,
            txtAge,
            txtEmailAddress,
            txtMobileNumber,
            txtAddress,
            txtContactPerson,
            txtContactPersonMobileNumber,
        ) = createRefs()

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(btnBack) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .padding(
                    start = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.large
                )
                .size(24.dp)
                .clickable { navigator.navigateUp()},
            tint = Black
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(randomPerson.picture?.largeProfile)
                .crossfade(true)
                .build(),
            placeholder = null,
            contentDescription = "",
            modifier = Modifier
                .constrainAs(imgProfile) {
                    centerHorizontallyTo(parent)
                    top.linkTo(btnBack.bottom)
                }
                .padding(top = MaterialTheme.spacing.large)
                .height(150.dp)
                .width(150.dp)
                .clip(CircleShape)
        )

        Text(
            modifier = Modifier
                .constrainAs(txtName) {
                    start.linkTo(parent.start)
                    top.linkTo(imgProfile.bottom)
                }
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ),
            text = "${stringResource(id = R.string.label_name)}: ${randomPerson.name?.firstName} ${randomPerson.name?.lastName}",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.W600
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(txtBirthday) {
                    start.linkTo(parent.start)
                    top.linkTo(txtName.bottom)
                }
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ),
            text = "${stringResource(id = R.string.label_birthday)}: ${
                DateUtil.convertDateFormat(
                    randomPerson.dateOfBirth?.date ?: ""
                )
            }",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.W600
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(txtAge) {
                    start.linkTo(parent.start)
                    top.linkTo(txtBirthday.bottom)
                }
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ),
            text = "${stringResource(id = R.string.label_age)}: ${randomPerson.dateOfBirth?.age}",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.W600
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(txtEmailAddress) {
                    start.linkTo(parent.start)
                    top.linkTo(txtAge.bottom)
                }
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ),
            text = "${stringResource(id = R.string.label_email_address)}: ${randomPerson.emailAddress}",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.W600
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(txtMobileNumber) {
                    start.linkTo(parent.start)
                    top.linkTo(txtEmailAddress.bottom)
                }
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ),
            text = "${stringResource(id = R.string.label_mobile_number)}: ${randomPerson.mobileNumber}",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.W600
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(txtAddress) {
                    start.linkTo(parent.start)
                    top.linkTo(txtMobileNumber.bottom)
                }
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ),
            text = "${stringResource(id = R.string.label_Address)}: ${randomPerson.address?.street?.number} " +
                    "${randomPerson.address?.street?.name}, ${randomPerson.address?.city} ${randomPerson.address?.state}, " +
                    "${randomPerson.address?.country}, ${randomPerson.address?.postCode}",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.W600
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(txtContactPerson) {
                    start.linkTo(parent.start)
                    top.linkTo(txtAddress.bottom)
                }
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ),
            text = "${stringResource(id = R.string.label_contact_person)}: ${randomPerson.contactPersonName?.name}",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.W600
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(txtContactPersonMobileNumber) {
                    start.linkTo(parent.start)
                    top.linkTo(txtContactPerson.bottom)
                }
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.medium
                ),
            text = "${stringResource(id = R.string.label_contact_person_mobile_number)}: ${randomPerson.contactPersonMobileNumber}",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.W600
            )
        )
    }
}
