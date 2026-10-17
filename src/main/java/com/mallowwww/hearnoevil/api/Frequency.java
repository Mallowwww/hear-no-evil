package com.mallowwww.hearnoevil.api;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public record Frequency(char band, short freq) {
    public static final Codec<Frequency> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.STRING.fieldOf("band").forGetter((Frequency f) -> (f.band + "").toUpperCase()),
            Codec.STRING.fieldOf("freq").forGetter((Frequency f) -> f.freq + "")
    ).apply(i, (band, freq) -> new Frequency(band.charAt(0), Short.parseShort(freq))));


    @Override
    public int hashCode() {
        return Objects.hashCode(band) ^ Objects.hashCode(freq);
    }

    public static Optional<Frequency> fromString(String s) {
        if (s.length() != 4) return Optional.empty();
        String p1 = s.substring(0, 1);
        String p2 = s.substring(1);
        if (p1.toUpperCase().matches("[A-Z]") && p2.matches("[0-9][0-9][0-9]")) {
            return Optional.of(new Frequency(
                    p1.toUpperCase().charAt(0),
                    Short.parseShort(p2)
            ));
        }
        return Optional.empty();
    }

    @Override
    public @NotNull String toString() {
        return (String.format("%s%03d", band, freq)).toUpperCase();
    }
    public static class Argument implements ArgumentType<Frequency> {

        @Override
        public Frequency parse(StringReader reader) throws CommandSyntaxException {
            return Frequency.fromString(reader.readUnquotedString()).orElse(null);
        }

        @Override
        public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
            return Suggestions.empty();
        }

        @Override
        public Collection<String> getExamples() {
            return Set.of("A330", "Z001", "F999", "J420");
        }
    }
}
