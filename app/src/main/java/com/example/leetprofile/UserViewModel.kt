package com.example.leetprofile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leetprofile.Dataclasses.BadgesResponse
import com.example.leetprofile.Dataclasses.ProblemStatsResponse
import com.example.leetprofile.Dataclasses.SubmissionResponse
import com.example.leetprofile.Dataclasses.account
import com.example.leetprofile.Repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val repository = UserRepository()

    var showLoadingDialog: (() -> Unit)? = null
    var hideLoadingDialog: (() -> Unit)? = null

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _userProfile = MutableLiveData<account>()
    val userProfile: LiveData<account> get() = _userProfile

    private val _userBadges = MutableLiveData<BadgesResponse>()
    val userBadges: LiveData<BadgesResponse> get() = _userBadges

    private val _problemStats = MutableLiveData<ProblemStatsResponse>()
    val problemStats: LiveData<ProblemStatsResponse> get() = _problemStats

    private val _submissions = MutableLiveData<SubmissionResponse>()
    val submissions: LiveData<SubmissionResponse> get() = _submissions

    private val _isProfileLoading = MutableLiveData<Boolean>()
    val isProfileLoading: LiveData<Boolean> get() = _isProfileLoading

    private val _isBadgesLoading = MutableLiveData<Boolean>()
    val isBadgesLoading: LiveData<Boolean> get() = _isBadgesLoading

    private val _isProblemStatsLoading = MutableLiveData<Boolean>()
    val isProblemStatsLoading: LiveData<Boolean> get() = _isProblemStatsLoading

    private val _isSubmissionsLoading = MutableLiveData<Boolean>()
    val isSubmissionsLoading: LiveData<Boolean> get() = _isSubmissionsLoading

    private val _profileError = MutableLiveData<String?>()
    val profileError: LiveData<String?> get() = _profileError

    private val _badgesError = MutableLiveData<String?>()
    val badgesError: LiveData<String?> get() = _badgesError

    private val _problemStatsError = MutableLiveData<String?>()
    val problemStatsError: LiveData<String?> get() = _problemStatsError

    private val _submissionsError = MutableLiveData<String?>()
    val submissionsError: LiveData<String?> get() = _submissionsError

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        username.observeForever { username ->
            if (!username.isNullOrEmpty()) {
                _isLoading.value = true
                showLoadingDialog?.invoke()
                fetchUserProfile(username)
                fetchUserBadges(username)
                fetchProblemStats(username)
                fetchSubmissions(username)
            }
        }
    }

    fun setUsername(user: String) {
        if (_username.value != user) {
            _username.value = user
        }
    }

    private fun fetchUserProfile(username: String) {
        viewModelScope.launch {
            try {
                _isProfileLoading.value = true
                _profileError.value = null

                val profile = repository.getUserProfile(username)
                _userProfile.value = profile
                Log.d("UserViewModel", "Fetched profile: $profile")

            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching profile: ${e.message}")
                _profileError.value = e.message
            } finally {
                _isProfileLoading.value = false
                checkAndUpdateLoadingState()
            }
        }
    }

    private fun fetchUserBadges(username: String) {
        viewModelScope.launch {
            try {
                _isBadgesLoading.value = true
                _badgesError.value = null

                val badges = repository.getUserBadges(username)
                _userBadges.value = badges
                Log.d("UserViewModel", "Fetched badges: $badges")

            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching badges: ${e.message}")
                _badgesError.value = e.message
            } finally {
                _isBadgesLoading.value = false
                checkAndUpdateLoadingState()
            }
        }
    }

    private fun fetchProblemStats(username: String) {
        viewModelScope.launch {
            try {
                _isProblemStatsLoading.value = true
                _problemStatsError.value = null

                val stats = repository.getProblemStats(username)
                _problemStats.value = stats
                Log.d("UserViewModel", "Fetched problem stats: $stats")

            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching problem stats: ${e.message}")
                _problemStatsError.value = e.message
            } finally {
                _isProblemStatsLoading.value = false
                checkAndUpdateLoadingState()
            }
        }
    }

    private fun fetchSubmissions(username: String) {
        viewModelScope.launch {
            try {
                _isSubmissionsLoading.value = true
                _submissionsError.value = null

                val submissions = repository.getSubmissions(username)
                _submissions.value = submissions
                Log.d("UserViewModel", "Fetched submissions: $submissions")

            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching submissions: ${e.message}")
                _submissionsError.value = e.message
            } finally {
                _isSubmissionsLoading.value = false
                checkAndUpdateLoadingState()
            }
        }
    }

    // Check if all operations are complete and update loading state
    private fun checkAndUpdateLoadingState() {
        val profileLoading = _isProfileLoading.value ?: false
        val badgesLoading = _isBadgesLoading.value ?: false
        val problemStatsLoading = _isProblemStatsLoading.value ?: false
        val submissionsLoading = _isSubmissionsLoading.value ?: false

        if (!profileLoading && !badgesLoading && !problemStatsLoading && !submissionsLoading) {
            _isLoading.value = false
            hideLoadingDialog?.invoke()
        }
    }

    override fun onCleared() {
        super.onCleared()
        username.observeForever {}
    }
}