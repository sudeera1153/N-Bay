package com.takg.nbay.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DrawerData(
    val icon: ImageVector? = null, val title: String? = null,
    val isDivider: Boolean = false, val isHeader: Boolean = false,
) {
    object Home : DrawerData(
        icon = Icons.Rounded.Home,
        title = "Home"
    )

    object Profile : DrawerData(
        icon = Icons.Rounded.AccountCircle,
        title = "Profile"
    )

    object MyListings : DrawerData(
        icon = Icons.Rounded.ViewList,
        title = "My Listings"
    )

    object Apparel : DrawerData(
        icon = Icons.Rounded.Checkroom,
        title = "Apparel"
    )

    object Electronics : DrawerData(
        icon = Icons.Rounded.ElectricalServices,
        title = "Electronics"
    )

    object JobsAndRecruitments : DrawerData(
        icon = Icons.Rounded.Work,
        title = "Jobs & Recruitments"
    )

    object Accommodation : DrawerData(
        icon = Icons.Rounded.Apartment,
        title = "Accommodation"
    )

    object Educational : DrawerData(
        icon = Icons.Rounded.School,
        title = "Educational"
    )

    object More : DrawerData(
        icon = Icons.Rounded.MoreHoriz,
        title = "More"
    )

    object Settings : DrawerData(
        icon = Icons.Rounded.Settings,
        title = "Settings"
    )

    object LogOut : DrawerData(
        icon = Icons.Rounded.Logout,
        title = "Log Out"
    )

    object Divider : DrawerData(
        isDivider = true
    )

    object HdrMyAccount : DrawerData(
        isHeader = true,
        title = "My Account"
    )

    object HdrCategories : DrawerData(
        isHeader = true,
        title = "Categories"
    )
}