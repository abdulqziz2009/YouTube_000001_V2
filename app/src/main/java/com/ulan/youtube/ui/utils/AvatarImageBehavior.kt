package com.ulan.youtube.ui.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.ulan.youtube.R
import de.hdodenhof.circleimageview.CircleImageView


@Suppress("unused")
class AvatarImageBehavior(private val mContext: Context, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<CircleImageView>() {
    private var mCustomFinalYPosition = 0f
    private var mCustomStartXPosition = 0f
    private var mCustomStartToolbarPosition = 0f
    private var mCustomStartHeight = 0f
    private var mCustomFinalHeight = 0f
    private var mAvatarMaxSize = 0f
    private val mFinalLeftAvatarPadding: Float
    private val mStartPosition = 0f
    private var mStartXPosition = 0
    private var mStartToolbarPosition = 0f
    private var mStartYPosition = 0
    private var mFinalYPosition = 0
    private var mStartHeight = 0
    private var mFinalXPosition = 0
    private var mChangeBehaviorPoint = 0f

    init {
        if (attrs != null) {
            val a = mContext.obtainStyledAttributes(attrs, R.styleable.AvatarImageBehavior)
            mCustomFinalYPosition =
                a.getDimension(R.styleable.AvatarImageBehavior_finalYPosition, 0f)
            mCustomStartXPosition =
                a.getDimension(R.styleable.AvatarImageBehavior_startXPosition, 0f)
            mCustomStartToolbarPosition =
                a.getDimension(R.styleable.AvatarImageBehavior_startToolbarPosition, 0f)
            mCustomStartHeight = a.getDimension(R.styleable.AvatarImageBehavior_startHeight, 0f)
            mCustomFinalHeight = a.getDimension(R.styleable.AvatarImageBehavior_finalHeight, 0f)
            a.recycle()
        }
        init()
        mFinalLeftAvatarPadding = mContext.resources.getDimension(
            R.dimen.spacing_normal
        )
    }

    private fun init() {
        bindDimensions()
    }

    private fun bindDimensions() {
        mAvatarMaxSize = mContext.resources.getDimension(R.dimen.image_width)
    }

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: CircleImageView,
        dependency: View
    ): Boolean {
        return dependency is Toolbar
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: CircleImageView,
        dependency: View
    ): Boolean {
        maybeInitProperties(child, dependency)
        val maxScrollDistance = mStartToolbarPosition.toInt()
        val expandedPercentageFactor = dependency.y / maxScrollDistance
        if (expandedPercentageFactor < mChangeBehaviorPoint) {
            val heightFactor =
                (mChangeBehaviorPoint - expandedPercentageFactor) / mChangeBehaviorPoint
            val distanceXToSubtract: Float = ((mStartXPosition - mFinalXPosition)
                    * heightFactor) + child.getHeight() / 2
            val distanceYToSubtract: Float = ((mStartYPosition - mFinalYPosition)
                    * (1f - expandedPercentageFactor)) + child.getHeight() / 2
            child.setX(mStartXPosition - distanceXToSubtract)
            child.setY(mStartYPosition - distanceYToSubtract)
            val heightToSubtract = (mStartHeight - mCustomFinalHeight) * heightFactor
            val lp = child.getLayoutParams() as CoordinatorLayout.LayoutParams
            lp.width = (mStartHeight - heightToSubtract).toInt()
            lp.height = (mStartHeight - heightToSubtract).toInt()
            child.setLayoutParams(lp)
        } else {
            val distanceYToSubtract = ((mStartYPosition - mFinalYPosition)
                    * (1f - expandedPercentageFactor)) + mStartHeight / 2
            child.setX((mStartXPosition - child.getWidth() / 2).toFloat())
            child.setY(mStartYPosition - distanceYToSubtract)
            val lp = child.getLayoutParams() as CoordinatorLayout.LayoutParams
            lp.width = mStartHeight
            lp.height = mStartHeight
            child.setLayoutParams(lp)
        }
        return true
    }

    private fun maybeInitProperties(child: CircleImageView, dependency: View) {
        if (mStartYPosition == 0) mStartYPosition = dependency.y.toInt()
        if (mFinalYPosition == 0) mFinalYPosition = dependency.height / 2
        if (mStartHeight == 0) mStartHeight = child.getHeight()
        if (mStartXPosition == 0) mStartXPosition = ((child.getX() + child.getWidth() / 2).toInt())
        if (mFinalXPosition == 0) mFinalXPosition =
            mContext.resources.getDimensionPixelOffset(org.koin.android.R.dimen.abc_action_bar_content_inset_material) + mCustomFinalHeight.toInt() / 2
        if (mStartToolbarPosition == 0f) mStartToolbarPosition = dependency.y
        if (mChangeBehaviorPoint == 0f) {
            mChangeBehaviorPoint =
                (child.getHeight() - mCustomFinalHeight) / (2f * (mStartYPosition - mFinalYPosition))
        }
    }

    val statusBarHeight: Int
        get() {
            var result = 0
            val resourceId =
                mContext.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = mContext.resources.getDimensionPixelSize(resourceId)
            }
            return result
        }

    companion object {
        private const val MIN_AVATAR_PERCENTAGE_SIZE = 0.3f
        private const val EXTRA_FINAL_AVATAR_PADDING = 80
        private const val TAG = "behavior"
    }
}