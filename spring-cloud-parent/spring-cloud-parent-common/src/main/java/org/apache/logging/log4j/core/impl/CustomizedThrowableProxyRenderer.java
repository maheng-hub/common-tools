package org.apache.logging.log4j.core.impl;

import org.apache.logging.log4j.core.pattern.TextRenderer;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

public class CustomizedThrowableProxyRenderer {
	private static final String TAB = "\t";
	private static final String CAUSED_BY_LABEL = "Caused by: ";
	private static final String SUPPRESSED_LABEL = "Suppressed: ";
	private static final String WRAPPED_BY_LABEL = "Wrapped by: ";

	private CustomizedThrowableProxyRenderer() {
		// Utility Class
	}

	@SuppressWarnings("ThrowableResultOfMethodCallIgnored")
	static void formatWrapper(final StringBuilder sb, final ThrowableProxy cause, final List<String> whiteListedPackages,
			final TextRenderer textRenderer, final String suffix, final String lineSeparator) {
		final Throwable caused = cause.getCauseProxy() != null ? cause.getCauseProxy().getThrowable() : null;
		if (caused != null) {
			formatWrapper(sb, cause.getCauseProxy(), whiteListedPackages, textRenderer, suffix, lineSeparator);
			sb.append(WRAPPED_BY_LABEL);
			renderSuffix(suffix, sb, textRenderer);
		}
		renderOn(cause, sb, textRenderer);
		renderSuffix(suffix, sb, textRenderer);
		textRenderer.render(lineSeparator, sb, "Text");
		formatElements(sb, Strings.EMPTY, cause.getCommonElementCount(),
				cause.getThrowable().getStackTrace(), cause.getExtendedStackTrace(), whiteListedPackages, textRenderer, suffix, lineSeparator);
	}

	private static void formatCause(final StringBuilder sb, final String prefix, final ThrowableProxy cause,
			final List<String> whiteListedPackages, final TextRenderer textRenderer, final String suffix, final String lineSeparator) {
		formatThrowableProxy(sb, prefix, CAUSED_BY_LABEL, cause, whiteListedPackages, textRenderer, suffix, lineSeparator);
	}

	private static void formatThrowableProxy(final StringBuilder sb, final String prefix, final String causeLabel,
			final ThrowableProxy throwableProxy, final List<String> whiteListedPackages,
			final TextRenderer textRenderer, final String suffix, final String lineSeparator) {
		if (throwableProxy == null) {
			return;
		}
		textRenderer.render(prefix, sb, "Prefix");
		textRenderer.render(causeLabel, sb, "CauseLabel");
		renderOn(throwableProxy, sb, textRenderer);
		renderSuffix(suffix, sb, textRenderer);
		textRenderer.render(lineSeparator, sb, "Text");
		formatElements(sb, prefix, throwableProxy.getCommonElementCount(),
				throwableProxy.getStackTrace(), throwableProxy.getExtendedStackTrace(), whiteListedPackages, textRenderer, suffix, lineSeparator);
		formatSuppressed(sb, prefix + TAB, throwableProxy.getSuppressedProxies(), whiteListedPackages, textRenderer, suffix, lineSeparator);
		formatCause(sb, prefix, throwableProxy.getCauseProxy(), whiteListedPackages, textRenderer, suffix, lineSeparator);
	}

	private static void formatSuppressed(final StringBuilder sb, final String prefix, final ThrowableProxy[] suppressedProxies,
			final List<String> whiteListedPackages, final TextRenderer textRenderer, final String suffix, final String lineSeparator) {
		if (suppressedProxies == null) {
			return;
		}
		for (final ThrowableProxy suppressedProxy : suppressedProxies) {
			formatThrowableProxy(sb, prefix, SUPPRESSED_LABEL, suppressedProxy, whiteListedPackages, textRenderer, suffix, lineSeparator);
		}
	}

	private static void formatElements(final StringBuilder sb, final String prefix, final int commonCount,
			final StackTraceElement[] causedTrace, final ExtendedStackTraceElement[] extStackTrace,
			final List<String> whiteListedPackages, final TextRenderer textRenderer, final String suffix, final String lineSeparator) {
		if (whiteListedPackages == null || whiteListedPackages.isEmpty()) {
			for (final ExtendedStackTraceElement element : extStackTrace) {
				formatEntry(element, sb, prefix, textRenderer, suffix, lineSeparator);
			}
		} else {
			int count = 0;
			for (int i = 0; i < extStackTrace.length; ++i) {
				if (whiteListedElement(causedTrace[i], whiteListedPackages)) {
					if (count > 0) {
						appendSuppressedCount(sb, prefix, count, textRenderer, suffix, lineSeparator);
						count = 0;
					}
					formatEntry(extStackTrace[i], sb, prefix, textRenderer, suffix, lineSeparator);
				} else {
					++count;
				}
			}
			if (count > 0) {
				appendSuppressedCount(sb, prefix, count, textRenderer, suffix, lineSeparator);
			}
		}
		if (commonCount != 0) {
			textRenderer.render(prefix, sb, "Prefix");
			textRenderer.render("\t... ", sb, "More");
			textRenderer.render(Integer.toString(commonCount), sb, "More");
			textRenderer.render(" more", sb, "More");
			renderSuffix(suffix, sb, textRenderer);
			textRenderer.render(lineSeparator, sb, "Text");
		}
	}

	private static void renderSuffix(final String suffix, final StringBuilder sb, final TextRenderer textRenderer) {
		if (!suffix.isEmpty()) {
			textRenderer.render(" ", sb, "Suffix");
			textRenderer.render(suffix, sb, "Suffix");
		}
	}

	private static void appendSuppressedCount(final StringBuilder sb, final String prefix, final int count,
			final TextRenderer textRenderer, final String suffix, final String lineSeparator) {
		textRenderer.render(prefix, sb, "Prefix");
		if (count == 1) {
			textRenderer.render("\t... ", sb, "Suppressed");
		} else {
			textRenderer.render("\t... suppressed ", sb, "Suppressed");
			textRenderer.render(Integer.toString(count), sb, "Suppressed");
			textRenderer.render(" lines", sb, "Suppressed");
		}
		renderSuffix(suffix, sb, textRenderer);
		textRenderer.render(lineSeparator, sb, "Text");
	}

	private static void formatEntry(final ExtendedStackTraceElement extStackTraceElement, final StringBuilder sb,
			final String prefix, final TextRenderer textRenderer, final String suffix, final String lineSeparator) {
		textRenderer.render(prefix, sb, "Prefix");
		textRenderer.render("\tat ", sb, "At");
		extStackTraceElement.renderOn(sb, textRenderer);
		renderSuffix(suffix, sb, textRenderer);
		textRenderer.render(lineSeparator, sb, "Text");
	}

	private static boolean whiteListedElement(final StackTraceElement element, final List<String> whiteListedPackages) {
		if (whiteListedPackages != null) {
			final String className = element.getClassName();
			for (final String pkg : whiteListedPackages) {
				if (className.startsWith(pkg)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Formats the stack trace including packaging information.
	 *
	 * @param src            ThrowableProxy instance to format
	 * @param sb             Destination.
	 * @param whiteListedPackages List of packages to be ignored in the trace.
	 * @param textRenderer   The message renderer.
	 * @param suffix         Append this to the end of each stack frame.
	 * @param lineSeparator  The end-of-line separator.
	 */
	public static void formatExtendedStackTraceTo(final ThrowableProxy src, final StringBuilder sb, final List<String> whiteListedPackages, final TextRenderer textRenderer, final String suffix, final String lineSeparator) {
		textRenderer.render(src.getName(), sb, "Name");
		textRenderer.render(": ", sb, "NameMessageSeparator");
		textRenderer.render(src.getMessage(), sb, "Message");
		renderSuffix(suffix, sb, textRenderer);
		textRenderer.render(lineSeparator, sb, "Text");
		final StackTraceElement[] causedTrace = src.getThrowable() != null ? src.getThrowable().getStackTrace() : null;
		formatElements(sb, Strings.EMPTY, 0, causedTrace, src.getExtendedStackTrace(), whiteListedPackages, textRenderer, suffix, lineSeparator);
		formatSuppressed(sb, TAB, src.getSuppressedProxies(), whiteListedPackages, textRenderer, suffix, lineSeparator);
		formatCause(sb, Strings.EMPTY, src.getCauseProxy(), whiteListedPackages, textRenderer, suffix, lineSeparator);
	}

	/**
	 * Formats the Throwable that is the cause of the <pre>src</pre> Throwable.
	 *
	 * @param src            Throwable whose cause to render
	 * @param sb             Destination to render the formatted Throwable that caused this Throwable onto.
	 * @param whiteListedPackages The List of packages to be suppressed from the stack trace.
	 * @param textRenderer   The text renderer.
	 * @param suffix         Append this to the end of each stack frame.
	 * @param lineSeparator  The end-of-line separator.
	 */
	static void formatCauseStackTrace(final ThrowableProxy src, final StringBuilder sb, final List<String> whiteListedPackages, final TextRenderer textRenderer, final String suffix, final String lineSeparator) {
		final ThrowableProxy causeProxy = src.getCauseProxy();
		if (causeProxy != null) {
			formatWrapper(sb, causeProxy, whiteListedPackages, textRenderer, suffix, lineSeparator);
			sb.append(WRAPPED_BY_LABEL);
			renderSuffix(suffix, sb, textRenderer);
		}
		renderOn(src, sb, textRenderer);
		renderSuffix(suffix, sb, textRenderer);
		textRenderer.render(lineSeparator, sb, "Text");
		formatElements(sb, Strings.EMPTY, 0, src.getStackTrace(), src.getExtendedStackTrace(),
				whiteListedPackages, textRenderer, suffix, lineSeparator);
	}

	private static void renderOn(final ThrowableProxy src, final StringBuilder output, final TextRenderer textRenderer) {
		final String msg = src.getMessage();
		textRenderer.render(src.getName(), output, "Name");
		if (msg != null) {
			textRenderer.render(": ", output, "NameMessageSeparator");
			textRenderer.render(msg, output, "Message");
		}
	}
}
